class TCPDescription:
    # per ip
    report_id = 0  # [PK, FK]
    ip_address = "NULL"  # PK
    port_number = -1
    is_tor_not_connected = 0  # Default 0
    is_time_out = 0  # Default false
    is_fin_bit_set = 0  # Default 0
    is_rst_bit_set = 0  # Default 0

    # If not censored then there should be iteration successful
    successful_iteration_number_local_server = 0  # Default 0 [Local Server]
    successful_iteration_number_tor = 0  # Default is 0 [Tor Browser]
    is_tor_connect_successful = 1

    # Middle box hop count
    middle_box_hop_count = -1

    # Is TCP Censored ... is_censored_TCP [INTEGER] in database .... to handle from report_arr
    is_censored_TCP = 0

    description_tcp = ""

    def getTCPDescription(self):
        self.description_tcp = ""
        self.description_tcp = self.description_tcp + "$ip_address:" + str(self.ip_address)
        self.description_tcp = self.description_tcp + "$port_number:" + str(self.port_number)
        self.description_tcp = self.description_tcp + "$is_tor_not_connected:" + str(self.is_tor_not_connected)
        self.description_tcp = self.description_tcp + "$is_time_out:" + str(self.is_time_out)
        self.description_tcp = self.description_tcp + "$is_fin_bit_set:" + str(self.is_fin_bit_set)
        self.description_tcp = self.description_tcp + "$is_rst_bit_set:" + str(self.is_rst_bit_set)
        self.description_tcp = self.description_tcp + "$successful_iteration_local_server:" + str(self.successful_iteration_number_local_server)
        self.description_tcp = self.description_tcp + "$successful_iteration_tor:" + str(self.successful_iteration_number_tor)
        self.description_tcp = self.description_tcp + "$is_tor_connect_successful:" + str(self.is_tor_connect_successful)
        self.description_tcp = self.description_tcp + "$middle_box_hop_count:" + str(self.middle_box_hop_count)
        self.description_tcp = self.description_tcp + "$is_censored_TCP:" + str(self.is_censored_TCP)
        return self.description_tcp
