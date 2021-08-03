package model.network;

import controller.IController;
import exception.PositionFormatException;
import util.Debug;
import util.Position;
import util.ShootType;
import view.OnlineView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class HostClient extends Thread
{
    protected IController controller;
    protected OnlineView onlineView;

    protected BufferedReader in;
    protected PrintWriter out;

    public HostClient(IController controller, OnlineView onlineView)
    {
        this.controller = controller;
        this.onlineView = onlineView;
    }

    public void close()
    {
        try
        {
            if (in != null && out != null)
            {
                in.close();
                out.close();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void send(String message)
    {
        out.println(message + '\n');
        out.flush();
    }

    //ATTACKING REQUEST PATTERN FOR CLIENTS OR HOSTS
    //1. Client Send Position
    //2. Host receive position
    //3. Host check content at position
    //4. Host send content at position (true, false)
    //5. Client receive content
    //6. Client change attack board

    public boolean RequestAttack(String position)
    {
        //1. Client Send Position
        send(position);
        //6. Client change attack board
        return Boolean.parseBoolean(receive());
    }

    //2. Host receive position
    public void receiveAttack()
    {
        new ReceiveAttack(this);
    }

    public void attack(String position)
    {
        try
        {
            //3. Host check content at position
            boolean bool = controller.shootPlayer1At(Position.parsePosition(position)) == ShootType.HIT;
            //4. Host send content at position (true, false)
            send(Boolean.toString(bool));
        }
        catch (PositionFormatException e)
        {
            e.printStackTrace();
        }
    }

    //5. Client receive content
    public String receive()
    {
        StringBuilder message = new StringBuilder();
        try
        {
            String line = in.readLine();
            while(line != null && line.length() > 0)
            {
                message.append(line);
                line = in.readLine();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        Debug.log("Received " + message);
        return message.toString();
    }
}