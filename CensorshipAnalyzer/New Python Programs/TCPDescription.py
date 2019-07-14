class TCPDescription:
    # per ip
    report_id = 0  # [PK, FK]
    ip_address = None  # PK
    is_tor_not_connected = 0  # Default 0

    is_time_out = 0  # Default false
    is_fin_bit_set = 0  # Default 0
    is_rst_bit_set = 0  # Default 0

    is_censored_TCP = 0  # Default NOT censored [false]
