import sys

import mymodule

mymodule.say_hi()
print("Version", mymodule.__version__)

a = dir(sys)
print(a)

