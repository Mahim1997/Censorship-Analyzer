from DNSDescription import DNSDescription
from HTTPDescription import HTTPDescription
from HTTPSDescription import HTTPSDescription
from TCPDescription import TCPDescription

# For report class
class Report:
    # Normal things
    report_id = 0  # Default [PK]
    connection_id = 0  # Default [FK]
    time_stamp = None
    url = None
    is_censored = 0  # False
    type_of_testing = None  # DNS, TCP, HTTP, HTTPS

    # For periodic things
    isPeriodic = 0  # False
    fileNamePeriodic = None  # Default
    iterationNumber = 0  # For file periodic checkings
    censorship_details = ""  # for now, nothing

    #  Objects for description
    dns_description = DNSDescription()
    tcp_description = TCPDescription()
    http_description = HTTPDescription()
    https_description = HTTPSDescription()