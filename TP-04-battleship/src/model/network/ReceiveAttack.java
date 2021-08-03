package model.network;

import util.Debug;

import java.io.IOException;

public class ReceiveAttack extends Thread
{
    private final HostClient hostClient;

    ReceiveAttack(HostClient hostClient)
    {
        this.hostClient = hostClient;
        this.start();
    }

    @Override
    public void run()
    {
        //2.
        StringBuilder message = new StringBuilder();
        try
        {
            String line = hostClient.in.readLine();
            while(line != null && line.length() > 0)
            {
                message.append(line);
                line = hostClient.in.readLine();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        Debug.log("Received " + message);
        hostClient.attack(message.toString());
    }
}