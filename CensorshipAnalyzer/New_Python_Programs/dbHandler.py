import sqlite3
import socket


class DBHandler:
    def handleReport_DNS(self, report):
        conn = sqlite3.connect("Client_DataBase.db")
        c = conn.cursor()

        c.execute("SELECT COUNT(*) FROM Report")  # Get the count from Report
        countGot = c.fetchall()
        num = -1
        for row in countGot:
            # print("--->>>>>>> In handleReport () ... Row[0] : " + str(row[0]))
            num = row[0]  # Don't know exactly why like this !!

        report.reportID = num + 1

        #  Inserting into Report Table
        c.execute("INSERT INTO Report VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                  (report.report_id, report.connection_id, report.time_stamp, report.url, report.type_of_testing,
                   report.is_censored, report.is_periodic, report.file_name_periodic, report.iteration_number,
                   report.censorship_details))

        #  Inserting into DNS Description

        c.execute("INSERT INTO DNS_DESCRIPTION VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                  (report.report_id, report.dns_description.is_timeout, report.dns_description.is_loopback,
                   report.dns_description.is_multicast, report.dns_description.is_broadcast,
                   report.dns_description.is_private,
                   report.dns_description.is_reserved, report.dns_description.is_nxDomain,
                   report.dns_description.is_noAnswerPacket,
                   report.dns_description.is_stubby_failed, report.dns_description.is_topExistingButAuthNotExisting,
                   report.dns_description.is_timeout_local_server, report.dns_description.is_non_overlapping_ip_list,
                   report.dns_description.middle_box_hop_count))

        #  Local IP list insertion
        for ip_local in report.dns_description.ip_address_list_local:
            c.execute("INSERT INTO DNS_IP_LIST_LOCAL VALUES(?, ?)",
                      report.report_id, ip_local)

        #  Remote IP list insertion
        for ip_stubby in report.dns_description.ip_address_list_local:
            c.execute("INSERT INTO DNS_IP_LIST_STUBBY VALUES(?, ?)",
                      report.report_id, ip_stubby)

        conn.commit()
        c.close()
        conn.close()

    def sendToJava(self, MESSAGE):
        # ---------- Transfer over socket to JAVA [JAVA Listening PORT = 7731] --------------
        UDP_IP = '127.0.0.1'  # loop back ip
        UDP_PORT = 7731  # Java listening port

        # MESSAGE = report.getReportString()

        print("----------------------------------- SENDING TO JAVA UDP ------------------------------------------")
        # print("UDP target IP:", UDP_IP)
        # print("UDP target port:", UDP_PORT)
        print("message:", MESSAGE)
        print("----------------------------------------------------------------------------------------------------")
        # print("Sending to Java UDP ... [7731] In dbHandler.py ")

        sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)  # UDP
        sock.sendto(bytes(MESSAGE, "utf-8"), (UDP_IP, UDP_PORT))
# -------------------------- Done sending to JAVA ------------------------------------------
