package com.darkmi.nio.channel;

import java.nio.channels.SelectionKey;
import java.io.IOException;

public interface TCPProtocol {
  void handleAccept(SelectionKey key) throws IOException;
  void handleRead(SelectionKey key) throws IOException;
  void handleWrite(SelectionKey key) throws IOException;
}
