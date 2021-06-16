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


def start_up(min_max_on_one_ski_lift, max_on_one_ski_lift):
    conn = db.connection()
    ski_lifts_count = db.get_skilifts_count(conn)

    for ski_lift_id in range(1, ski_lifts_count+1):
        for _ in range(10, randint(min_max_on_one_ski_lift, max_on_one_ski_lift)):
            actual_time = int(time.time()) - randint(30, 300)
            timestamp = datetime.datetime.fromtimestamp(actual_time).strftime('%Y-%m-%d %H:%M:%S')
            db.insert_to_usages(conn, randint(1, db.get_passes_count(conn)), ski_lift_id,
                                str(timestamp))
            time.sleep(0.02)





def simulation(pause_value):
    actual_time = int(time.time())
    mad = datetime.datetime.fromtimestamp(actual_time).strftime('%Y-%m-%d %H:%M:%S')
    conn = db.connection()
    passes = db.get_passes(conn)
    db.insert_to_usages(conn, 3, 2, str(mad))
    passes_count = db.get_passes_count(conn)
    index_skilifts = db.get_skilifts_count(conn)
    # print(" SKI LIFTSSSS = " + str(index_skilifts))
    # print(" PASEES = " + str(passes_count))

    while 1 == 1:
        actual_time = int(time.time())
        timestamp = datetime.datetime.fromtimestamp(actual_time).strftime('%Y-%m-%d %H:%M:%S')
        db.insert_to_usages(conn, randint(1, db.get_passes_count(conn)), randint(1, db.get_skilifts_count(conn)),
                            str(timestamp))
        time.sleep(pause_value)


def initialize_view(tk, wrapper):
    start_up(20, 30)
    wrapper.pack(fill="both", expand="yes", padx=20, pady=20)


    btn_simulation_low = Button(wrapper, text="low",
                                command=lambda: simulation(1.5))
    btn_simulation_low.place(relx=0.45, rely=0.06)

    btn_simulation_medium = Button(wrapper, text="medium",
                                   command=lambda: simulation(1))
    btn_simulation_medium.place(relx=0.45, rely=0.40)

    btn_simulation_high = Button(wrapper, text="high",
                                 command=lambda: simulation(0.5))
    btn_simulation_high.place(relx=0.45, rely=0.76)