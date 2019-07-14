# Client-side database
class DBCreator:
    conn = None
    c = None

    def __init__(self):
        # Establish connection with the database
        self.conn = sqlite3.connect("Client Data.db")
        self.c = conn.cursor()

    def dropTables(self):
        c.execute("DROP TABLE IF EXISTS Network")
        c.execute("DROP TABLE IF EXISTS USER")

        c.execute("DROP TABLE IF EXISTS Connection")
        c.execute("DROP TABLE IF EXISTS Report")
        c.execute("DROP TABLE IF EXISTS DNSDescription")
        c.execute("DROP TABLE IF EXISTS LocalIpAddresses")
        c.execute("DROP TABLE IF EXISTS StubbyIpAddresses")
        c.execute("DROP TABLE IF EXISTS HTTPDescription")
        c.execute("DROP TABLE IF EXISTS TCPDescription")
        

    def closeConnection(self):
        self.c.close()
        self.conn.close()