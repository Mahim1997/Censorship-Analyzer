class TCPDescription:
    # per ip
    report_id = 0  # [PK, FK]
    ip_address = None  # PK
    is_tor_not_connected = 0  # Default 0
    port_number = 80

    is_time_out = 0  # Default false
    is_fin_bit_set = 0  # Default 0
    is_rst_bit_set = 0  # Default 0

    # If not censored then there should be iteration successful
    successful_iteration_number = 0  # Default 0

    is_tor_connect_successfull = 1

    # Middle box hop count
    middle_box_hop_count = 0

    def getTCPDescription(self):
        return "NULL_FOR_NOW"
