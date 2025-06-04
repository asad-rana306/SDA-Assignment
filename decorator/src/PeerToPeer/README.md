
### versions/

- **V1_SinglePeer_SingleMessage.java**
    - Launches a server socket on port 9000 that accepts one connection, reads one line, then quits.
    - Also, as a client, opens a socket to `localhost:9000`, sends one hard-coded message, then exits.
    - *Single message only; no console loop.*

- **V2_SplitIntoTwoPeers_MultiMessage.java**
    - Starts a server thread on port 9000 and, in the main thread, reads lines from the console until `exit`.
    - Each typed line is sent to `localhost:9000`.
    - *Demonstrates multi-message in one file, but still not split into two peers.*

### PeerA.java & PeerB.java

These files represent the **final two‐peer setup**:

- **PeerA.java**
    - Listens on port 9000.
    - Sends any console text to `localhost:9001`.
    - Type `exit` to stop sending.

- **PeerB.java**
    - Listens on port 9001.
    - Sends any console text to `localhost:9000`.
    - Type `exit` to stop sending.

#### To compile & run (same machine, two terminals):

1. Compile both:
   ```bash
   cd "P2P Lab"
   javac PeerA.java
   javac PeerB.java
