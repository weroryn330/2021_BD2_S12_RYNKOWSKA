from tkinter import *
import json
import datetime
from datetime import date
from random import randint
import time
import psycopg2
import database_ski_lift as db

class Time:
    def __init__(self):
        self.time = datetime.datetime.now()

    def get(self):
        return self.time


def start_up(min_max_on_one_ski_lift, max_on_one_ski_lift, user, all_used_ids):
    conn = db.connection(user.get_username(), user.get_password())
    ski_lifts_count = db.get_skilifts_count(conn)
    conn.close()
    index = 55
    for ski_lift_id in range(1, ski_lifts_count+1):
        for _ in range(10, randint(min_max_on_one_ski_lift, max_on_one_ski_lift)):
            all_used_ids.append(index)
            actual_time = int(time.time()) - randint(30, 300)
            timestamp = datetime.datetime.fromtimestamp(actual_time).strftime('%Y-%m-%d %H:%M:%S')
            conn = db.connection(user.get_username(), user.get_password())
            db.insert_to_usages(conn, index, randint(1, db.get_passes_count(conn)), ski_lift_id,
                                str(timestamp))
            conn.close()
            index += 1
            time.sleep(0.02)





def simulation(pause_value, user:db.User, delta_t:int, all_used_ids):
    start_time_unix = int(time.time())
    index = 1000
    try:
        while (int(time.time()) - delta_t) < start_time_unix:
            all_used_ids.append(index)
            actual_time = int(time.time())
            timestamp = datetime.datetime.fromtimestamp(actual_time).strftime('%Y-%m-%d %H:%M:%S')
            conn = db.connection(user.get_username(), user.get_password())
            db.insert_to_usages(conn, index, randint(1, db.get_passes_count(conn)),
                                randint(1, db.get_skilifts_count(conn)), str(timestamp))
            conn.close()
            index += 1
            time.sleep(pause_value)
    except KeyboardInterrupt:
        pass


def initialize_view(user: db.User, delta_t: int, pause_sec: float):
    all_used_ids = []
    start_up(20, 30, user, all_used_ids)
    simulation(pause_sec, user, delta_t, all_used_ids)
    conn = db.connection(user.get_username(), user.get_password())
    db.delete_mockup_usages(conn, all_used_ids)
    conn.close()
