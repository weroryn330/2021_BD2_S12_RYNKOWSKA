
import new_view as v
from tkinter import *
import tkinter as tk
import database_ski_lift as db

def main():
    root = tk.Tk()

    print("Python mockup invoked!")
    wrapper = LabelFrame(root, text="Symulejszyn")
    wrapper_skilifts = LabelFrame(root, text="Ski Lifts")
    v.initialize_view(tk, wrapper)

    root.title("DOSKOZAAAA")
    root.geometry("300x200")
    root.mainloop()

if __name__ == "__main__":
    main()

# def convertTuple(tuple):
#     str = ''.join(tuple)
#     return str
#
# conn = db.connection()
# db.get_passes(conn)
# id_passes = db.get_id_passes_for_specified_user(conn, 1)
#
# for id_ in list(id_passes):
#     db.get_passes_for_specified_pass(conn, id_[0])
#
# conn.commit()
# conn.close()



