from DNSDescription import DNSDescription
from HTTPDescription import HTTPDescription
from HTTPSDescription import HTTPSDescription
from TCPDescription import TCPDescription


# For report class
class Report:
    # Normal things
    report_id = 0  # Default [PK]
    connection_id = 0  # Default [FK]
    time_stamp = "NULL"
    url = "NULL"
    type_of_testing = "NULL"  # DNS, TCP, HTTP, HTTPS
    is_censored = 0  # False

    # For periodic things
    is_file_check = 0  # Default False [useless in database]
    is_periodic = 0  # False
    file_name_periodic = "NULL"  # Default
    iteration_number = 0  # For file periodic checkings
    censorship_details = ""  # for now, nothing

    #  Objects for description
    dns_description = DNSDescription()
    tcp_description = TCPDescription()
    http_description = HTTPDescription()
    https_description = HTTPSDescription()

    # Final objects for descripiton
    # DNS stays as it is
    # TCP arrays of descriptions ...
    tcp_description_arr = []
    http_description_arr = []
    # Final report string
    str_report = ""

    def getReportString(self):
        self.str_report = ""
        self.str_report = "ReportID:" + str(self.report_id)  # 0
        self.str_report = self.str_report + "$ConnectionID:" + str(self.connection_id)  # 1
        self.str_report = self.str_report + "$TimeStamp:" + str(self.time_stamp)  # 2
        self.str_report = self.str_report + "$URL:" + self.url  # 3
        self.str_report = self.str_report + "$Type_of_Test:" + self.type_of_testing  # 4
        self.str_report = self.str_report + "$is_censored:" + str(self.is_censored)  # 5
        self.str_report = self.str_report + "$is_file_check:" + str(self.is_file_check)  # 7
        self.str_report = self.str_report + "$is_periodic:" + str(self.is_periodic)  # 8
        self.str_report = self.str_report + "$file_name_periodic:" + self.file_name_periodic  # 9
        self.str_report = self.str_report + "$iteration_number:" + str(self.iteration_number)  # 10
        self.str_report = self.str_report + "$censorshipDetails:" + self.censorship_details  # 11

        if self.type_of_testing == "DNS":  # Lines 13 to the rest
            self.str_report = self.str_report + "$middle_box_hop_count:" + str(
                self.dns_description.middle_box_hop_count)  # 12
            self.dns_description.formDNSDescription()
            self.str_report = self.str_report + self.dns_description.str_dns_description

        elif self.type_of_testing == "TCP":  # Lines 13 to the rest
            self.str_report = self.str_report + "$middle_box_hop_count:" + str(
                self.tcp_description.middle_box_hop_count)  # 12
            # TO DO !!! [CHANGE AS DNS]
            str_tcp_description = ""
            for desc in self.tcp_description_arr:
                str_tcp_description = str_tcp_description + desc.getTCPDescription()
            # str_tcp_description = self.tcp_description.getTCPDescription()
            self.str_report = self.str_report + str_tcp_description

        return self.str_report

    def copyFromReport(self, report):
        self.report_id = report.report_id
        self.connection_id = report.connection_id
        self.time_stamp = report.time_stamp
        self.url = report.url
        self.type_of_testing = report.type_of_testing
        self.is_censored = report.is_censored
        self.is_file_check = report.is_file_check
        self.is_periodic = report.is_periodic
        self.file_name_periodic = report.file_name_periodic
        self.iteration_number = report.iteration_number
        self.censorship_details = report.censorship_details

    def printReport(self):
        print("------------------------------- Printing Report Begin -------------------------------------")
        strReport = self.getReportString()
        print(strReport)
        print("------------------------------- Printing Report Done -------------------------------------")
