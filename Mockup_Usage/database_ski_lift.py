import psycopg2
import datetime as datetime
import time

class User:
    def __init__(self, username, password):
        self.username = username
        self.password = password

    def get_username(self):
        return self.username

    def get_password(self):
        return self.password


def connection(user, password):
    conn = psycopg2.connect(
        host="localhost",
        database="Ski_Resort",
        user=user,
        password=password)
    return conn


def print_list(objects):
    for one_object in objects:
        print(one_object[2])


def check(passes):
    for one_pass in passes:
        if str(one_pass[2]) <= "0":
            return False
        if one_pass[0] is not None and one_pass[1] is not None:
            start_date_unix = datetime.datetime.timestamp(one_pass[0])
            end_date_unix = datetime.datetime.timestamp(one_pass[1])
            if int(start_date_unix) > int(time.time()) or int(time.time()) > int(end_date_unix):
                return False
        if (one_pass[3]) == "1":
            return False
        return True


def get_passes(conn):
    cur = conn.cursor()
    cur.execute('SELECT p.id_pass, p.start_date, p.end_date, p.uses_left, u.use_timestamp, s.name '
                'FROM passes p LEFT OUTER JOIN usages u ON p.id_pass = u.passes_id_invoice_item '
                'LEFT OUTER JOIN ski_lifts s ON u.ski_lift_id_ski_lift = s.id_ski_lift')
    skilifts = cur.fetchall()
    return skilifts


def get_passes_for_specified_pass(conn, id_pass):
    cur = conn.cursor()
    cur.execute('SELECT p.id_pass, p.start_date, p.end_date, p.uses_left, u.use_timestamp, s.name '
                'FROM passes p LEFT OUTER JOIN usages u ON p.id_pass = u.passes_id_invoice_item '
                'LEFT OUTER JOIN ski_lifts s ON u.ski_lift_id_ski_lift = s.id_ski_lift '
                'WHERE id_pass = ' + str(id_pass))
    skilifts = cur.fetchall()
    return skilifts


def get_id_passes_for_specified_user(conn, id_user):
    cur = conn.cursor()
    cur.execute('SELECT distinct p.id_pass FROM passes p RIGHT OUTER JOIN invoices i ON '
                'i.id_invoice = p.invoices_id_invoice RIGHT OUTER JOIN users ON '
                'i.users_id_user = ' + str(id_user))
    id_passes = cur.fetchall()
    return id_passes


def update_start_date_in_passes(conn, date, id_pass):
    cur = conn.cursor()
    cur.execute("UPDATE passes SET start_date ='" + date + "' WHERE id_pass =" + str(id_pass))


def update_end_date_in_passes(conn, date, id_pass):
    cur = conn.cursor()
    cur.execute("UPDATE passes SET end_date ='" + date + "' WHERE id_pass =" + str(id_pass))


def update_passes_uses(conn, usages, id_pass):
    cur = conn.cursor()
    cur.execute("UPDATE passes SET uses_left = " + str(usages) + " WHERE id_pass = " + str(id_pass))


def check_if_pass_is_ok(conn, pass_id):
    cur=conn.cursor()

    cur.execute("SELECT start_date, end_date, uses_left, blocked FROM passes where id_pass = " + str(pass_id))
    passes = cur.fetchall()
    return check(passes)


def insert_to_usages(conn, index, pass_id, ski_lift_id, time_stamp):
    cur = conn.cursor()
    try:
        if check_if_pass_is_ok(conn, pass_id) == True:
            cur.execute("SELECT uses_left FROM passes where id_pass = " + str(pass_id))
            passes = cur.fetchall()
            for one_pass in passes:
                cur.execute("UPDATE passes SET uses_left = " + str(int(one_pass[0])-1) + " WHERE id_pass = " + str(pass_id))
            cur.execute("INSERT INTO usages(id_usage, passes_id_invoice_item, ski_lift_id_ski_lift, use_timestamp, success_flag) "
                            "VALUES ("+ str(index) +"," + str(pass_id) + "," + str(ski_lift_id) + ",'" + time_stamp +"', 1)")
            conn.commit()
    except:
       print("Exception: id_usage " + str(index) + " already exists ")


def get_skilifts_count(conn):
    cur = conn.cursor()
    cur.execute('SELECT MAX(id_ski_lift) FROM ski_lifts')
    ski_lifts_count = cur.fetchall()
    return ski_lifts_count[0][0]


def get_passes_count(conn):
    cur = conn.cursor()
    cur.execute('SELECT MAX(id_pass) FROM passes')
    ski_lifts_count = cur.fetchall()
    return ski_lifts_count[0][0]


def delete_mockup_usages(conn, all_used_ids):
    cur = conn.cursor()
    for _id in all_used_ids:
        cur.execute("DELETE FROM usages WHERE id_usage = " + str(_id))
    conn.commit()





