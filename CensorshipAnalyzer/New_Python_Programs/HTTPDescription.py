class HTTPDescription:
	# per ip
	ip_address = []
	is_other_error = 0  # Default false
	http_response_code_local = []
	http_response_code_tor = []
	message_http = ""   # Default nothing

	is_fin_bit_set = 0
	is_rst_bit_set = 0

	redirection_history_local = []
	redirection_history_tor = []

	threshold_comarison = 0.300  # Default comparison threshold is 30 %
	is_different_wrt_threshold = 0  # Default False
	content_difference = 0.0
	is_max_redirection_reached = 0

	# Middle box hop count
	middle_box_hop_count = 0
