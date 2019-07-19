class DNSDescription:
    report_id = 0  # FK, PK
    # These below fields are the conditions
    is_timeout = 0
    is_loopback = 0
    is_multicast = 0
    is_broadcast = 0
    is_private = 0
    is_bogon = 0
    is_unknown_error = 0
    is_reserved = 0
    is_nxDomain = 0
    is_noAnswerPacket = 0
    is_stubby_failed = 0
    is_topExistingButAuthNotExisting = 0  # Top level DNS Server has this domain but the authorative dns servers do not

    # Ip address lists
    ip_address_list_local = []
    ip_address_list_stubby = []

    # IS overlapping or not ?
    is_non_overlapping_ip_list = 0  # Default 0/False

    # Middle box hop count
    middle_box_hop_count = 0
