class HTTPDescription:
    # per ip
    ip_address = None
    is_no_response_GET_request = 0  # Default false

    message_http = ""   # Default nothing

    is_fin_bit_set = 0
    is_rst_bit_set = 0

    content_local_server = None
    content_tor_browser = None

    threshold_comarison = 0.300  # Default comparison threshold is 30 %
    is_different_wrt_threshold = 0  # Default False
    is_censored_HTTP = 0  # Default FALSE