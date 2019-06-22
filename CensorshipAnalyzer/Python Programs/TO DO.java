
        //File Read ... Read each line 
        String command = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(this.absFileName))) {
            String url = reader.readLine();
            int index = 1;
            while (url != null) {
//                System.out.println(line);
                // read next line
                url = reader.readLine();

                if (url != null) {
                    //Send to python as command ...
                }

            }
        } catch (IOException ex) {
            Notification.push("Error", "Error in reading file", Notification.FAILURE, Pos.CENTER);
            return;
        }

//        Notification.push("Passing Through for single URL", command, Notification.SUCCESS, Pos.CENTER);
        //Send to Server_UDP
        JavaUDPServerClient.sendCommandToPython(command);