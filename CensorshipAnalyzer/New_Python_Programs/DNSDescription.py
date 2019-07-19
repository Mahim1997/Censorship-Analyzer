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
    is_timeout_local_server = 0

    # IS overlapping or not ?
    is_non_overlapping_ip_list = 0  # Default 0/False
    is_first_8_to_24_bit_match = 0  # Default False

    # Middle box hop count
    middle_box_hop_count = 0

    # Ip address lists
    ip_address_list_local = []
    ip_address_list_stubby = []

    str_dns_description = ""

    def __init__(self):
        self.str_dns_description = ""

    def formDNSDescription(self):
        self.str_dns_description = ""
        self.str_dns_description = self.str_dns_description + "$is_timeOut:" + str(self.is_timeout)
        self.str_dns_description = self.str_dns_description + "$is_loopback:" + str(self.is_loopback)
        self.str_dns_description = self.str_dns_description + "$is_multicast:" + str(self.is_multicast)
        self.str_dns_description = self.str_dns_description + "$is_broadcast:" + str(self.is_broadcast)
        self.str_dns_description = self.str_dns_description + "$is_private:" + str(self.is_private)
        self.str_dns_description = self.str_dns_description + "$is_bogon:" + str(self.is_bogon)
        self.str_dns_description = self.str_dns_description + "$is_unknownError:" + str(self.is_unknown_error)
        self.str_dns_description = self.str_dns_description + "$is_reserved:" + str(self.is_reserved)
        self.str_dns_description = self.str_dns_description + "$is_nxDomain:" + str(self.is_nxDomain)
        self.str_dns_description = self.str_dns_description + "$is_noAnswerPacket:" + str(self.is_noAnswerPacket)
        self.str_dns_description = self.str_dns_description + "$is_stubby_failed:" + str(self.is_stubby_failed)
        self.str_dns_description = self.str_dns_description + "$is_topExistingButAuthNotExisting:" + str(self.is_topExistingButAuthNotExisting)
        self.str_dns_description = self.str_dns_description + "$is_timeout_local_server:" + str(self.is_timeout_local_server)
        self.str_dns_description = self.str_dns_description + "$is_first_8_to_24_bit_match:" + str(self.is_first_8_to_24_bit_match)

        # Now the ip list local
        self.str_dns_description = self.str_dns_description + "$ip_address_list_local:"
        for ip in self.ip_address_list_local:
            self.str_dns_description = self.str_dns_description + str(ip) + ","

        # Now the ip list stubby
        self.str_dns_description = self.str_dns_description + "$ip_address_list_stubby:"
        for ip in self.ip_address_list_stubby:
            self.str_dns_description = self.str_dns_description + str(ip) + ","

        self.str_dns_description = self.str_dns_description + "$is_non_overlapping_ip_list:" + str(self.is_non_overlapping_ip_list)
        self.str_dns_description = self.str_dns_description + "$middle_box_hop_count:" + str(self.middle_box_hop_count)

        return self.str_dns_description
