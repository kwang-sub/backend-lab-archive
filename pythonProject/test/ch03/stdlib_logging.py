import logging
import os.path
import platform

if platform.platform().startswith('Windows'):
    logging_file = os.path.join(os.getenv('HOMEDRIVE'),
                                os.getenv("HOMEPATH"),
                                'test.log')
else:
    logging_file = os.path.join(os.getenv("HOME"), 'test.log')

print("Logging to ", logging_file)

logging.basicConfig(
    level=logging.DEBUG,
    format='%(asctime)s : %(levelname)s : %(message)s',
    filename = logging_file,
    filemode = 'w',
)

logging.debug("Start of the program")
logging.info("Doing something")
logging.warning("Dying now")

def get_error_details():
    return (2, 'details')

errnum, errstr = get_error_details()
print(errnum)

a = 5; b = 8
print(a, b)