package com.example.ivan.cloudmobile.ctrl;

import android.content.Context;

import com.example.ivan.cloudmobile.R;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public abstract class SendData {

    static String sendData(Context context, String socketConnection, String obj) {
        String info[] = socketConnection.split(":");
        String answer = context.getString(R.string.server_error);

        if (info.length == 2) {
            String address = info[0];
            int port = Integer.parseInt(info[1]);
            System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " +address +"/" +port);

            Socket connection = null;
            DataOutputStream out = null;
            try {
                connection = new Socket(address, port);
                out = new DataOutputStream(connection.getOutputStream());

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

                out.writeBytes(obj + "\n");

                answer = in.readLine();
                System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " +answer);
                connection.close();

                return answer;
            } catch (IOException e) { e.printStackTrace(); }
        }

        return answer;
    }
}