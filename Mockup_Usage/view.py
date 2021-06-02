from tkinter import *
import json
import datetime


class Time:
    def __init__(self):
        self.time = datetime.datetime.now()

    def set(self):
        self.time = datetime.datetime.now()

    def get(self):
        return self.time


timestamp_base = "0000-00-00 00:00:02"
uses_base = 10

my_json_string = {
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
      }

   ]
}


def get_passes_for_user(name, surname):
    if name == "Tomcio" and surname == "Paluch":
        return my_json_string["passes"]


def insert_passes_to_listbox(name_tk, surname_tk, lsb):
    name = name_tk.get()
    surname = surname_tk.get()

    lsb.delete('0', 'end')
    passes = get_passes_for_user(name, surname)
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


def enter_skilift(lsb, skilift_name, name_tk, surname_tk, enter_time: Time):
    selected_pass_id = lsb.get(lsb.curselection())
    name = name_tk.get()
    surname = surname_tk.get()
    todays_date = Time()

    passes = get_passes_for_user(name, surname)
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
                        insert_passes_to_listbox(name_tk, surname_tk, lsb)
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


def exit_skilift(enter_time: Time, lsb, name_tk, surname_tk):
    selected_pass_id = lsb.get(lsb.curselection())
    exit_time = Time()
    time_delta_exit_enter = exit_time.get() - enter_time.get()
    passes = get_passes_for_user("Tomcio", "Paluch")
    for one_pass in passes:
        if one_pass["pass_id"] == str(selected_pass_id):
            if one_pass["timestamp"] is not None:
                time_delta_exit_enter_as_datetime = datetime.datetime.strptime(str(time_delta_exit_enter)[:7], "%H:%M:%S")
                timestamp_only_hours_into_datetime = datetime.datetime.strptime(one_pass["timestamp"][11:], "%H:%M:%S")
                if timestamp_only_hours_into_datetime < time_delta_exit_enter_as_datetime:
                    one_pass["timestamp"] = "0000-00-00 00:00:00"
                    print("You finished your session in: " + str(time_delta_exit_enter)[:7] + "\n")
                    print("You have no time left on pass, sorry!")
                    insert_passes_to_listbox(name_tk, surname_tk, lsb)
                else:
                    new_time_left = timestamp_only_hours_into_datetime - time_delta_exit_enter
                    one_pass["timestamp"] = str(one_pass["timestamp"])[:11] + str(new_time_left)[11:19]
                    print("You finished your session in: " + str(time_delta_exit_enter)[:7] + "\n")
                    print("Your time left on the pass is: " + str(one_pass["timestamp"])[:19])
                    insert_passes_to_listbox(name_tk, surname_tk, lsb)
            elif one_pass["uses"] is not None:
                print("Hope you had a blast!")


def initialize_view(tk, wrapper, wrapper_skilifts):
    init_time = Time()
    name_from_entry = StringVar()
    surname_from_entry = StringVar()

    wrapper.pack(fill="both", expand="yes", padx=20, pady=20)
    wrapper_skilifts.pack(fill="both", expand="yes", padx=20, pady=20)
    lbl_name = Label(wrapper, text="Name")
    lbl_name.place(relx=0.01, rely=0.01)
    lbl_surname = Label(wrapper, text="Surname")
    lbl_surname.place(relx=0.01, rely=0.11)
    ent_name = Entry(wrapper, textvariable=name_from_entry)
    ent_name.place(relx=0.11, rely=0.01)
    ent_surname = Entry(wrapper, textvariable=surname_from_entry)
    ent_surname.place(relx=0.11, rely=0.11)

    lbl_passes = Label(wrapper, text="Passes:")
    lbl_passes.place(relx=0.01, rely=0.21)
    lsb_passes = Listbox(wrapper, width=50)
    lsb_passes.place(relx=0.11, rely=0.21)

    btn_load_names = Button(wrapper, text="Load",
                            command=lambda: insert_passes_to_listbox(name_from_entry, surname_from_entry, lsb_passes))
    btn_load_names.place(relx=0.3, rely=0.06)

    lbl_skilift_name1 = Label(wrapper_skilifts, text="Cisowianka")
    lbl_skilift_name1.place(relx=0.01, rely=0.01)
    lbl_skilift_name1 = Label(wrapper_skilifts, text="Kinia Pieninska")
    lbl_skilift_name1.place(relx=0.01, rely=0.11)
    btn_skilift1_enter = Button(wrapper_skilifts, text="Enter",
                                command=lambda: enter_skilift(lsb_passes, "Cisowianka", name_from_entry, surname_from_entry, init_time))
    btn_skilift1_enter.place(relx=0.11, rely=0.01)
    btn_skilift1_exit = Button(wrapper_skilifts, text="Exit",
                               command=lambda: exit_skilift(init_time, lsb_passes, name_from_entry, surname_from_entry))
    btn_skilift1_exit.place(relx=0.21, rely=0.01)
    btn_skilift2_enter = Button(wrapper_skilifts, text="Enter",
                                command=lambda: enter_skilift(lsb_passes, "Kinia Pieninska", name_from_entry, surname_from_entry, init_time))
    btn_skilift2_enter.place(relx=0.11, rely=0.11)
    btn_skilift2_exit = Button(wrapper_skilifts, text="Exit",
                               command=lambda: exit_skilift(init_time, lsb_passes, name_from_entry, surname_from_entry))
    btn_skilift2_exit.place(relx=0.21, rely=0.11)


