import sqlite3


# Client-side database
conn = sqlite3.connect("Client_DataBase.db")
c = conn.cursor()


def dropTables():
    c.execute("DROP TABLE IF EXISTS Network")
    c.execute("DROP TABLE IF EXISTS USER")

    c.execute("DROP TABLE IF EXISTS Connection")
    c.execute("DROP TABLE IF EXISTS Report")
    c.execute("DROP TABLE IF EXISTS DNSDescription")
    c.execute("DROP TABLE IF EXISTS LocalIpAddresses")
    c.execute("DROP TABLE IF EXISTS StubbyIpAddresses")
    c.execute("DROP TABLE IF EXISTS TCPDescription")

    c.execute("DROP TABLE IF EXISTS HTTPDescription")
    
    conn.commit()

def createTables():
    # For Network
    print("Creating tables ... ")
    c.execute(
        "CREATE TABLE IF NOT EXISTS NETWORK(global_ip TEXT PRIMARY KEY, asn TEXT, city TEXT, organization "
        "TEXT, carrier_name TEXT, latitude TEXT, longitude TEXT, country_name TEXT, region TEXT, "
        "postal TEXT)")
    # For User
    c.execute(
        "CREATE TABLE IF NOT EXISTS USER(user_id INTEGER PRIMARY KEY, user_name TEXT, email_address TEXT, "
        "password TEXT )")
    # For Connection
    c.execute("CREATE TABLE IF NOT EXISTS CONNECTION(connection_id INTEGER PRIMARY KEY, global_ip TEXT, "
                   "user_id INTEGER, FOREIGN KEY(global_ip) REFERENCES NETWORK(global_ip), FOREIGN KEY(user_id) "
                   "REFERENCES USER(user_id) )")
    # For Report
    c.execute(
        "CREATE TABLE IF NOT EXISTS REPORT(report_id INTEGER PRIMARY KEY, connection_id INTEGER, timestamp "
        "TEXT, url TEXT, type_of_testing TEXT, is_censored INTEGER, is_periodic INTEGER, file_name_periodic "
        "TEXT, iteration_number INTEGER, censorship_details TEXT, FOREIGN KEY(connection_id) REFERENCES "
        "CONNECTION(connection_id))")

    # DNS Description
    c.execute("CREATE TABLE IF NOT EXISTS DNS_DESCRIPTION(report_id INTEGER PRIMARY KEY, is_timeout INTEGER, "
                   "is_loopback INTEGER, is_multicast INTEGER, is_broadcast INTEGER, is_private INTEGER, "
                   "is_bogon INTEGER, is_unknown_error INTEGER , is_nxDomain INTEGER, is_noAnswerPacket INTEGER, "
                   "is_stubby_failed INTEGER, is_topExistingButAuthNotExisting INTEGER, is_timeout_local_server INTEGER, "
                   "is_non_overlapping_ip_list INTEGER, is_first_8_to_24_bit_match INTEGER, FOREIGN KEY(report_id) REFERENCES REPORT(report_id))")

    # Inside DNS Description Tables [DNS_IP_LIST_LOCAL: Local IP List]
    c.execute("CREATE TABLE IF NOT EXISTS DNS_IP_LIST_LOCAL(report_id INTEGER, ip_address TEXT, PRIMARY KEY"
                   "(report_id, ip_address), FOREIGN KEY(report_id) REFERENCES REPORT(report_id) )")

    # Inside DNS Description Tables [DNS_IP_LIST_STUBBY: Remote IP List]
    c.execute("CREATE TABLE IF NOT EXISTS DNS_IP_LIST_STUBBY(report_id INTEGER, ip_address TEXT, PRIMARY KEY"
                   "(report_id, ip_address), FOREIGN KEY(report_id) REFERENCES REPORT(report_id) )")

    # TCP Description [per ip address]
    c.execute("CREATE TABLE IF NOT EXISTS TCP_DESCRIPTION(report_id INTEGER, ip_address TEXT, port INTEGER, "
                   "is_tor_not_censored INTEGER, is_time_out INTEGER, is_fin_bit_set INTEGER, is_rst_bit_set INTEGER, "
                   "successful_iteration_number_local_server INTEGER, successful_iteration_number_tor INTEGER, "
                   "is_tor_connect_successful INTEGER, middle_box_hop_count INTEGER, "
                   "is_censored_TCP INTEGER, PRIMARY KEY(report_id, ip_address), FOREIGN KEY(report_id) REFERENCES "
                   "REPORT(report_id))")

    # HTTP Description [per ip address]
    c.execute("CREATE TABLE IF NOT EXISTS HTTP_DESCRIPTION(report_id INTEGER, ip_address TEXT, "
                   "is_no_response_GET_request INTEGER, message_HTTP TEXT, is_fin_bit_set INTEGER, is_rst_bit_set "
                   "INTEGER, content_localServer TEXT, content_TOR_Browser TEXT, threshold_for_comparison TEXT, "
                   "is_different_wrt_threshold INTEGER, is_censored_HTTP INTEGER, PRIMARY KEY(report_id, ip_address), "
                   "FOREIGN KEY(report_id) REFERENCES REPORT(report_id) )")

    conn.commit()
    print("Done Creating Tables ... ")


def createForHTTP():
  c.execute("DROP TABLE IF EXISTS HTTP_DESCRIPTION")
  conn.commit()
  # HTTP Description [per ip address]
  c.execute("CREATE TABLE IF NOT EXISTS HTTP_DESCRIPTION(report_id INTEGER, ip_address TEXT, "
                 "http_response_code_local TEXT, http_response_code_tor TEXT, is_other_error TEXT, message_HTTP TEXT , is_fin_bit_set INTEGER, is_rst_bit_set "
                 "INTEGER, redirection_history_local TEXT, redirection_history_tor TEXT, threshold_comparison TEXT, "
                 "is_different_wrt_threshold TEXT, content_difference TEXT, is_max_redirection_reached TEXT, max_redirection_count TEXT, middle_box_hop_count TEXT, PRIMARY KEY(report_id, ip_address), "
                 "FOREIGN KEY(report_id) REFERENCES REPORT(report_id) )")
  conn.commit() 


def createForHTTPS():
  c.execute("DROP TABLE IF EXISTS HTTPS_DESCRIPTION")
  conn.commit()
  # HTTP Description [per ip address]
  c.execute("CREATE TABLE IF NOT EXISTS HTTPS_DESCRIPTION(report_id INTEGER, ip_address TEXT, "
                 "https_response_code_local TEXT, https_response_code_tor TEXT, is_other_error TEXT, message_HTTPS TEXT , is_fin_bit_set INTEGER, is_rst_bit_set "
                 "INTEGER, redirection_history_local TEXT, redirection_history_tor TEXT, threshold_comparison TEXT, "
                 "is_different_wrt_threshold TEXT, content_difference TEXT, is_max_redirection_reached TEXT, max_redirection_count TEXT, middle_box_hop_count TEXT,  is_tls_handshake_failed TEXT, PRIMARY KEY(report_id, ip_address), "
                 "FOREIGN KEY(report_id) REFERENCES REPORT(report_id) )")
  conn.commit() 



def closeConnection():
    c.close()
    conn.close()


isMain: bool = False
if isMain:
    dropTables()
    createTables()
    closeConnection()


isHTTPCreator = False
if isHTTPCreator:
  createForHTTP()
  createForHTTPS()
  closeConnection()


print("DONE RUNNING clientDBCraete.py FILE")