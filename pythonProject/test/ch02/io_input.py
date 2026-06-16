import re
from getpass import _raw_input


def reverse(text):
    return text[::-1]

def is_palindrome(text):
    text = text.replace(" ", "")
    text = re.sub(r'[^a-zA-Z0-9]','',text)
    text = text.lower()
    return text == reverse(text)

something = _raw_input("Enter text: ")
if is_palindrome(something):
    print("Yes, it is a palindrome")
else:
    print("No, it is not a palindrome")

