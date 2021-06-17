from tkinter import *
import json
import datetime
from datetime import date
from random import randint
import time


class Time:
    def __init__(self):
        self.time = datetime.datetime.now()

    def set(self):
        self.time = datetime.datetime.now()

    def get(self):
        return self.time


timestamp_base = "0000-00-00 00:02:02"
uses_base = 10

tomek_passes = {
   "passes": [
      {
          "pass_id": "1",
          "start_date": "2021-01-01",
          "end_date": "2021-12-12",
          "uses": None,
          "timestamp": timestamp_base,
          "skilift": "Kinia Pieninska"
      },

      {
          "pass_id": "2",
          "start_date": "2021-01-01",
          "end_date": "2021-12-12",
          "uses": str(uses_base),
          "timestamp": None,
          "skilift": "Cisowianka"
      },

      {
          "pass_id": "3",
          "start_date": "2020-01-01",
          "end_date": "2020-12-12",
          "uses": str(uses_base),
          "timestamp": None,
          "skilift": "Cisowianka"
      },

      {
          "pass_id": "4",
          "start_date": "2021-01-01",
          "end_date": "2021-12-12",
          "uses": str(uses_base),
          "timestamp": None,
          "skilift": "Cisowianka"
      },

      {
          "pass_id": "5",
          "start_date": "2021-01-01",
          "end_date": "2021-12-12",
          "uses": str(uses_base),
          "timestamp": None,
          "skilift": "Cisowianka"
      },

      {
          "pass_id": "6",
          "start_date": "2021-01-01",
          "end_date": "2021-12-12",
          "uses": str(uses_base),
          "timestamp": None,
          "skilift": "Cisowianka"
      },

   ]
}


maria_passes = {
   "passes": [
      {
          "pass_id": "1",
          "start_date": "2021-01-01",
          "end_date": "2021-12-12",
          "uses": None,
          "timestamp": timestamp_base,
          "skilift": "Kinia Pieninska"
      },

      {
          "pass_id": "2",
          "start_date": "2021-01-01",
          "end_date": "2021-12-12",
          "uses": str(uses_base),
          "timestamp": None,
          "skilift": "Cisowianka"
      },

      {
          "pass_id": "3",
          "start_date": "2020-01-01",
          "end_date": "2020-12-12",
          "uses": str(uses_base),
          "timestamp": None,
          "skilift": "Cisowianka"
      },
   ]
}


all_users = {
    "users": [
      {
          "user_id": "1",
          "name": "Tomcio",
          "surname": "Paluch"
      },

      {
          "user_id": "2",
          "name": "Maria",
          "surname": "Kery"
      },
    ]
}

def get_passes_for_user(id):
    if id == "1":
        return tomek_passes["passes"]
    elif id == "2":
        return maria_passes["passes"]


def insert_passes_to_listbox(user_id, lsb):
    lsb.delete('0', 'end')
    passes = get_passes_for_user(user_id)
    iterator = 1
    for one_pass in passes:
        if one_pass["uses"] is not None:
            lsb.insert(iterator, one_pass["pass_id"])
            lsb.insert(iterator + 1, "start-date: " + str(one_pass["start_date"]) +"  end-date: " + str(one_pass["end_date"]))
            lsb.insert(iterator + 2, "uses: " + str(one_pass["uses"]))
            lsb.insert(iterator + 3, "skilift: " + one_pass["skilift"])
        if one_pass["timestamp"] is not None:
            lsb.insert(iterator, one_pass["pass_id"])
            lsb.insert(iterator + 1, "start-date: " + str(one_pass["start_date"]) +"  end-date: " + str(one_pass["end_date"]))
            lsb.insert(iterator + 2, "time left: " + str(one_pass["timestamp"]))
            lsb.insert(iterator + 3, "skilift: " + one_pass["skilift"])
        iterator += 4


def get_time():
    return datetime.datetime.now()


def enter_skilift(lsb, skilift_name, user_id, enter_time: Time, pass_id):
    #selected_pass_id = lsb.get(lsb.curselection())
    selected_pass_id = pass_id
    todays_date = Time()

    passes = get_passes_for_user(user_id)
    for one_pass in passes:
        if one_pass["pass_id"] == str(selected_pass_id):
            todays_date_as_datetime = datetime.datetime.strptime(str(todays_date.get())[:10], "%Y-%m-%d")
            pass_start_date_as_datetime = datetime.datetime.strptime(str(one_pass["start_date"]), "%Y-%m-%d")
            pass_end_date_as_datetime = datetime.datetime.strptime(str(one_pass["end_date"]), "%Y-%m-%d")
            if pass_start_date_as_datetime <= todays_date_as_datetime <= pass_end_date_as_datetime:
                if one_pass["skilift"] == skilift_name and one_pass["uses"] is not None:
                    if one_pass["uses"] != "0":
                        print("You successfully entered " + skilift_name + ".")
                        one_pass["uses"] = str(int(one_pass["uses"]) - 1)
                        print("Number of passes left: " + one_pass["uses"] + ".")
                        insert_passes_to_listbox(user_id, lsb)
                        return
                    else:
                        print("Your pass has no uses left!")
                        return

                elif one_pass["skilift"] == skilift_name and one_pass["timestamp"] is not None:
                    if one_pass["timestamp"] != "0000-00-00 00:00:00":
                        enter_time.set()
                        x = enter_time.get()
                        print("You successfully entered " + skilift_name + " at " + str(x)[:19] + ".")

                        return
                    else:
                        print("Your time on pass has ended!")
                        return

                elif one_pass["skilift"] != skilift_name:
                    print("You musn't enter " + skilift_name + "\nThis pass is for " + one_pass["skilift"] + " only.")
                    return
            else:
                print("You pass isn't active!")
                return
    print("Internal Server Error")


def exit_skilift(enter_time: Time, lsb, user_id, pass_id):
    #selected_pass_id = lsb.get(lsb.curselection())
    selected_pass_id = pass_id
    exit_time = Time()
    time_delta_exit_enter = exit_time.get() - enter_time.get()
    passes = get_passes_for_user(user_id)
    for one_pass in passes:
        if one_pass["pass_id"] == str(selected_pass_id):
            if one_pass["timestamp"] is not None:
                time_delta_exit_enter_as_datetime = datetime.datetime.strptime(str(time_delta_exit_enter)[:7], "%H:%M:%S")
                timestamp_only_hours_into_datetime = datetime.datetime.strptime(one_pass["timestamp"][11:], "%H:%M:%S")
                if timestamp_only_hours_into_datetime < time_delta_exit_enter_as_datetime:
                    one_pass["timestamp"] = "0000-00-00 00:00:00"
                    print("You finished your session in: " + str(time_delta_exit_enter)[:7] + "\n")
                    print("You have no time left on pass, sorry!")
                    insert_passes_to_listbox(user_id, lsb)
                else:
                    new_time_left = timestamp_only_hours_into_datetime - time_delta_exit_enter
                    one_pass["timestamp"] = str(one_pass["timestamp"])[:11] + str(new_time_left)[11:19]
                    print("You finished your session in: " + str(time_delta_exit_enter)[:7] + "\n")
                    print("Your time left on the pass is: " + str(one_pass["timestamp"])[:19])
                    insert_passes_to_listbox(user_id, lsb)
            elif one_pass["uses"] is not None:
                print("Hope you had a blast!")


def check_if_pass_is_in_use(passes, idd):
    for one_pass in passes:
        if one_pass == idd:
            return "is being used"
    return "is not used"


def get_index_of_pass(passes, idd):
    index = 0
    for one_pass in passes:
        if one_pass == idd:
            return index
        index += 1


def get_skilift_from_pass(value, user_id):
    passes = get_passes_for_user(user_id)
    for one_pass in passes:
        if one_pass["pass_id"] == str(value):
            return one_pass["skilift"]


def get_name_from_pass(value, user_id):
    passes = get_passes_for_user(user_id)
    for one_pass in passes:
        if one_pass["pass_id"] == str(value):
            return one_pass["name"]


def get_surname_from_pass(value, user_id):
    passes = get_passes_for_user(user_id)
    for one_pass in passes:
        if one_pass["pass_id"] == str(value):
            return one_pass["surname"]


def get_start_date_from_pass(value, user_id):
    passes = get_passes_for_user(user_id)
    for one_pass in passes:
        if one_pass["pass_id"] == str(value):
            return one_pass["start_date"]


def get_end_date_from_pass(value, user_id):
    passes = get_passes_for_user(user_id)
    for one_pass in passes:
        if one_pass["pass_id"] == str(value):
            return one_pass["end_date"]


def get_uses_from_pass(value, user_id):
    passes = get_passes_for_user(user_id)
    for one_pass in passes:
        if one_pass["pass_id"] == str(value):
            return one_pass["uses"]


def get_timestamp_from_pass(value, user_id):
    passes = get_passes_for_user(user_id)
    for one_pass in passes:
        if one_pass["pass_id"] == str(value):
            return one_pass["timestamp"]


def get_users_count():
    users = all_users["users"]
    count = 0
    for user in users:
        count += 1
    return count


def get_passes_count(user_id):
    passes = get_passes_for_user(user_id)
    count = 0
    for one_pass in passes:
        count += 1
    return count


def get_user_name(user_id):
    users = all_users["users"]
    for user in users:
        if user["user_id"] == user_id:
            return user["name"]


def get_user_surname(user_id):
    users = all_users["users"]
    for user in users:
        if user["user_id"] == user_id:
            return user["surname"]


def simulation(init_time, lsb, user_id, pause_value):
    todays_date = datetime.datetime.strptime(date.today().strftime("%Y-%m-%d"), "%Y-%m-%d")
    passes_being_used = []
    for _ in range(10):
        user_id = str(randint(1, get_users_count()))
        for _ in range(randint(1, 3)):
            value = randint(1, get_passes_count(user_id))

            start_date = datetime.datetime.strptime(get_start_date_from_pass(value, user_id), "%Y-%m-%d")
            end_date = datetime.datetime.strptime(get_end_date_from_pass(value, user_id), "%Y-%m-%d")

            if check_if_pass_is_in_use(passes_being_used, value) == "is not used":
                skilift_name = get_skilift_from_pass(value, user_id)
                print("User " + get_user_name(user_id) + " " + get_user_surname(user_id) + " enters " + skilift_name +
                      " using pass with id " + str(value))
                if get_timestamp_from_pass(value, user_id) is not None:
                    if start_date <= todays_date <= end_date:

                        enter_skilift(lsb, skilift_name, user_id, init_time, value)
                        if get_timestamp_from_pass(value, user_id) != "0000-00-00 00:00:00":
                            passes_being_used.append(value)
                            #increase_skilift_users_count(skilift_name)
                    else:
                        print("This pass isnt active!")
                elif get_uses_from_pass(value, user_id) is not None:
                    if start_date <= todays_date <= end_date:
                        skilift_name = get_skilift_from_pass(value, user_id)
                        enter_skilift(lsb, skilift_name, user_id, init_time, value)
                        if str(get_uses_from_pass(value, user_id)) != "0":
                            passes_being_used.append(value)
                    else:
                        print("This pass isnt active!")
            else:
                skilift_name = get_skilift_from_pass(value, user_id)
                print("User " + get_user_name(user_id) + " " + get_user_surname(user_id) + " enters " + skilift_name +
                      " using pass with id " + str(value))
                passes_being_used.pop(get_index_of_pass(passes_being_used, value))
                exit_skilift(init_time, lsb, user_id, value)
                # decrease_skilift_users_count(skilift_name)
            time.sleep(pause_value)




def initialize_view(tk, wrapper, wrapper_skilifts):
    init_time = Time()
    user_id_from_entry = StringVar()

    wrapper.pack(fill="both", expand="yes", padx=20, pady=20)
    wrapper_skilifts.pack(fill="both", expand="yes", padx=20, pady=20)
    lbl_user_id = Label(wrapper, text="Name")
    lbl_user_id.place(relx=0.01, rely=0.01)
    ent_user_id = Entry(wrapper, textvariable=user_id_from_entry)
    ent_user_id.place(relx=0.11, rely=0.01)


    lbl_passes = Label(wrapper, text="Passes:")
    lbl_passes.place(relx=0.01, rely=0.21)
    lsb_passes = Listbox(wrapper, width=50)
    lsb_passes.place(relx=0.11, rely=0.21)
    btn_load_names = Button(wrapper, text="Load",
                            command=lambda: insert_passes_to_listbox(user_id_from_entry, lsb_passes))
    btn_load_names.place(relx=0.3, rely=0.06)

    btn_simulation_low = Button(wrapper, text="low",
                            command=lambda: simulation(init_time, lsb_passes, user_id_from_entry, 5))
    btn_simulation_low.place(relx=0.6, rely=0.06)

    btn_simulation_medium = Button(wrapper, text="medium",
                                command=lambda: simulation(init_time, lsb_passes, user_id_from_entry, 3))
    btn_simulation_medium.place(relx=0.6, rely=0.16)

    btn_simulation_high = Button(wrapper, text="high",
                                command=lambda: simulation(init_time, lsb_passes, user_id_from_entry, 1))
    btn_simulation_high.place(relx=0.6, rely=0.26)


    lbl_skilift_name1 = Label(wrapper_skilifts, text="Cisowianka")
    lbl_skilift_name1.place(relx=0.01, rely=0.01)
    lbl_skilift_name1 = Label(wrapper_skilifts, text="Kinia Pieninska")
    lbl_skilift_name1.place(relx=0.01, rely=0.11)
    btn_skilift1_enter = Button(wrapper_skilifts, text="Enter",
                                command=lambda: enter_skilift(lsb_passes, "Cisowianka", user_id_from_entry, init_time, 1))
    btn_skilift1_enter.place(relx=0.11, rely=0.01)
    btn_skilift1_exit = Button(wrapper_skilifts, text="Exit",
                               command=lambda: exit_skilift(init_time, lsb_passes, user_id_from_entry, 1))
    btn_skilift1_exit.place(relx=0.21, rely=0.01)
    btn_skilift2_enter = Button(wrapper_skilifts, text="Enter",
                                command=lambda: enter_skilift(lsb_passes, "Kinia Pieninska", user_id_from_entry, init_time, 1))
    btn_skilift2_enter.place(relx=0.11, rely=0.11)
    btn_skilift2_exit = Button(wrapper_skilifts, text="Exit",
                               command=lambda: exit_skilift(init_time, lsb_passes, user_id_from_entry, 1))
    btn_skilift2_exit.place(relx=0.21, rely=0.11)


