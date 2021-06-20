
import new_view as v
from tkinter import *
import tkinter as tk
import database_ski_lift as db
import sys


def main():
    user = db.User(str(sys.argv[1]), str(sys.argv[2]))
    pause = float(sys.argv[3])
    delta_t = int(sys.argv[4])
    v.initialize_view(user, delta_t, pause)


main()



