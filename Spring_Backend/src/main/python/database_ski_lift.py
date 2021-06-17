import psycopg2

def connection():
    conn = psycopg2.connect(
        host="localhost",
        database="Ski_Resort",
        user="postgres",
        password="root")
    return conn


def print_list(objects):
    for one_object in objects:
        print(one_object)


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

def insert_to_usages(conn, passes_id, ski_lift_id, time_stamp):
    cur = conn.cursor()
    cur.execute("INSERT INTO usages(passes_id_invoice_item, ski_lift_id_ski_lift, use_timestamp, success_flag) "
                "VALUES (" + str(passes_id) + "," + str(ski_lift_id) + ",'" + time_stamp +"', 1)")
    conn.commit()


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





