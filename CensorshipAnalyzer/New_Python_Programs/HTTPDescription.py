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

	max_redirection_count = 0

	# Middle box hop count
	middle_box_hop_count = 0

	str_description = ""

	def __init__(self):
		self.ip_address = []
		self.is_other_error = 0  # Default false
		self.http_response_code_local = []
		self.http_response_code_tor = []
		self.message_http = ""  # Default nothing

		self.is_fin_bit_set = 0
		self.is_rst_bit_set = 0

		self.redirection_history_local = []
		self.redirection_history_tor = []

		self.threshold_comarison = 0.300  # Default comparison threshold is 30 %
		self.is_different_wrt_threshold = 0  # Default False
		self.content_difference = 0.0
		self.is_max_redirection_reached = 0

		self.max_redirection_count = 0

		# Middle box hop count
		self.middle_box_hop_count = 0

		self.str_description = ""

	def getIPAddressesHashed(self):
		str_ret = ""
		for ip in self.ip_address:
			str_ret = str_ret + str(ip) + "#"
		return str_ret

	def getHTTPResponseCodeLocalHashed(self):
		str_ret = ""
		for ip in self.http_response_code_local:
			str_ret = str_ret + str(ip) + "#"
		return str_ret

	def getHTTPResponseCodeTorHashed(self):
		str_ret = ""
		for ip in self.http_response_code_tor:
			str_ret = str_ret + str(ip) + "#"
		return str_ret

	def getRedirectionHistoryLocalHashed(self):
		str_ret = ""
		for ip in self.redirection_history_local:
			str_ret = str_ret + str(ip) + "#"
		return str_ret

	def getRedirectionHistoryTorHashed(self):
		str_ret = ""
		for ip in self.redirection_history_tor:
			str_ret = str_ret + str(ip) + "#"
		return str_ret


	def getHTTPDescription_String(self):
		self.str_description = self.str_description + "$ip_address:" + self.getIPAddressesHashed()
		self.str_description = self.str_description + "$http_response_code_local:" + self.getHTTPResponseCodeLocalHashed()
		self.str_description = self.str_description + "$http_response_code_tor:" + self.getHTTPResponseCodeTorHashed()
		self.str_description = self.str_description + "$is_other_error:" + str(self.is_other_error)
		self.str_description = self.str_description + "$message_HTTP:" + str(self.message_http)
		self.str_description = self.str_description + "$is_fin_bit_set:" + str(self.is_fin_bit_set)
		self.str_description = self.str_description + "$is_rst_bit_set:" + str(self.is_rst_bit_set)

		self.str_description = self.str_description + "$redirection_history_local#" + self.getRedirectionHistoryLocalHashed()
		self.str_description = self.str_description + "$redirection_history_tor#" + self.getRedirectionHistoryTorHashed()

		self.str_description = self.str_description + "$threshold_comparison:" + str(self.threshold_comarison)
		self.str_description = self.str_description + "$is_different_wrt_threshold:" + str(self.is_different_wrt_threshold)
		self.str_description = self.str_description + "$content_difference:" + str(self.content_difference)
		self.str_description = self.str_description + "$is_max_redirection_reached:" + str(self.is_max_redirection_reached)
		self.str_description = self.str_description + "$max_redirection_count:" + str(self.max_redirection_count)
		self.str_description = self.str_description + "$middle_box_hop_count:" + str(self.middle_box_hop_count)

		return self.str_description