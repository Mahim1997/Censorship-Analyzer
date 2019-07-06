class TCP_Description:
	msg_to_set = ""
	ip_addresses_resolved = []
	successIter_tor_list_http = []
	successIter_tor_list_https = []
	successIter_ls_list_http = []
	successIter_ls_list_https = []
	censored_arr_http = []
	censored_arr_https = []
	hop_count_http = -1
	hop_count_https = -1

	def form_Description(self, msg_to_set, ip_addresses_resolved, successIter_tor_list_http, successIter_ls_list_http, 
		successIter_tor_list_https, successIter_ls_list_https, censored_arr_http, censored_arr_https,
		hop_count_http, hop_count_https):
		self.msg_to_set = msg_to_set
		self.ip_addresses_resolved = ip_addresses_resolved
		self.successIter_ls_list_https = successIter_ls_list_https
		self.successIter_tor_list_https = successIter_tor_list_https
		self.successIter_ls_list_http = successIter_ls_list_http
		self.successIter_tor_list_http = successIter_tor_list_http
		self.censored_arr_http = censored_arr_http
		self.censored_arr_https = censored_arr_https
		self.hop_count_https = hop_count_https
		self.hop_count_http = hop_count_http
