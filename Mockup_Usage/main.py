
import view as v
from tkinter import *
import tkinter as tk

root = tk.Tk()

wrapper = LabelFrame(root, text="Symulejszyn")
wrapper_skilifts = LabelFrame(root, text="Ski Lifts")

v.initialize_view(tk, wrapper, wrapper_skilifts)

root.title("DOSKOZAAAA")
root.geometry("1024x720")
root.mainloop()




