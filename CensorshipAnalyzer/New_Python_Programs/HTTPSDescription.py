class HTTPSDescription:
	ip_address = []
	https_response_code_local = []
	https_response_code_tor = []
	is_other_error = 0  # Default false
	message_https = ""   # Default nothing

	is_fin_bit_set = 0
	is_rst_bit_set = 0
	is_tls_handshake_failed = 0

	redirection_history_local = []
	redirection_history_tor = []

	threshold_comarison = 0.300  # Default comparison threshold is 30 %
	is_different_wrt_threshold = 0  # Default False
	content_difference = 0.0
	is_max_redirection_reached = 0

	# Middle box hop count
	middle_box_hop_count = 0
