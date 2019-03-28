# bbs (Bulletin Board System)

Developing a distributed client/server version of news bulletin board system using a service-oriented request/reply scheme (RPC/RMI).

## Running and Testing
For testing on multiple machines I used Docker to make many containers that each will represent a reader or writer client.

To run:
```bash
$ ./start.sh
```

The `start.sh` script will do the following:
* Creates a virtual and isolated network between containers.
* Setup the master machine (That will run Start.java) and generate a private and public key for it.
* Start creating the server and clients machines (containers), copy the public key of the master machine to them, so the master machine can ssh into them.
* Start the Start.java program on the master machine.
* Sleep 20s until all clients are done.
* Print the server readers and writers logs.

Sample output:
```
===================================================
Creating docker container for server ..
8b2d100c9088d2ade8cf5b7e0ee7ada3efa9aa31fa2e06c20708d05eecde86ca
Done.
===================================================
Creating docker containers for clients ..
d733ff721dfc65e0c20758477f17db06a69637a4aa526176baf6ae1b697de335
50dd8d5ae886d54308f6694e2eb0e9ad7af73830d6af64998e08c421f42cc583
688e5e302e1339a264a97438ca461c1663627e191ce4bcabdc4f1f932adffde1
085ea0885de676a350989c22cbf3fa4cc84f09e3c1fbb4643c6f49fb07dea50e
e49c3ee0e966491d6b68fe25ea9bb83b0fa7954f9d6b21a61eae99bde084b910
7898a1c1dbb369383a391ff61389d048e4dcd3dfb9cbb89a6e3216c7e88705ae
1d06c27117458ef43a74cecc287929a2527f0e11c078e71c8f3fc15c60458264
f5119af00a77723eeaac12ae8c8e0a0fc0f3279335c50dddc0b1869f71dd15be
Done.
===================================================
CONTAINER ID        IMAGE               COMMAND               CREATED             STATUS                  PORTS               NAMES
f5119af00a77        bbs-image           "/usr/sbin/sshd -D"   1 second ago        Up Less than a second   22/tcp, 1099/tcp    writer7
1d06c2711745        bbs-image           "/usr/sbin/sshd -D"   1 second ago        Up Less than a second   22/tcp, 1099/tcp    writer6
7898a1c1dbb3        bbs-image           "/usr/sbin/sshd -D"   2 seconds ago       Up 1 second             22/tcp, 1099/tcp    writer5
e49c3ee0e966        bbs-image           "/usr/sbin/sshd -D"   3 seconds ago       Up 2 seconds            22/tcp, 1099/tcp    writer4
085ea0885de6        bbs-image           "/usr/sbin/sshd -D"   4 seconds ago       Up 3 seconds            22/tcp, 1099/tcp    reader3
688e5e302e13        bbs-image           "/usr/sbin/sshd -D"   5 seconds ago       Up 4 seconds            22/tcp, 1099/tcp    reader2
50dd8d5ae886        bbs-image           "/usr/sbin/sshd -D"   6 seconds ago       Up 5 seconds            22/tcp, 1099/tcp    reader1
d733ff721dfc        bbs-image           "/usr/sbin/sshd -D"   7 seconds ago       Up 6 seconds            22/tcp, 1099/tcp    reader0
8b2d100c9088        bbs-image           "/usr/sbin/sshd -D"   8 seconds ago       Up 7 seconds            22/tcp, 1099/tcp    server
73fcc5ddd3de        bbs-master-image    "/usr/sbin/sshd -D"   13 seconds ago      Up 12 seconds           22/tcp, 1099/tcp    bbs_master
===================================================
Starting ..
Cleaning prev server logs ..
server SSH command: ssh server cd /home/src ;java bbs/Server server 8080 1099
Wait until server is fully initialized ..
Done
reader SSH command: ssh writer4 cd /home/src ;java bbs/Writer 3 4 server 1099
reader SSH command: ssh writer5 cd /home/src ;java bbs/Writer 3 5 server 1099
reader SSH command: ssh writer6 cd /home/src ;java bbs/Writer 3 6 server 1099
reader SSH command: ssh writer7 cd /home/src ;java bbs/Writer 3 7 server 1099
writer SSH command: ssh reader0 cd /home/src ;java bbs/Reader 3 0 server 1099
writer SSH command: ssh reader1 cd /home/src ;java bbs/Reader 3 1 server 1099
writer SSH command: ssh reader2 cd /home/src ;java bbs/Reader 3 2 server 1099
writer SSH command: ssh reader3 cd /home/src ;java bbs/Reader 3 3 server 1099
Sleeping 20s until all clients are done ..
Done.
===================================================
SERVER READERS LOGS
sSeq	 oVal	 rID	 rNum
1	 -1 	 0	 1
2	 -1 	 1	 2
5	 6 	 3	 1
8	 7 	 0	 2
9	 7 	 1	 3
10	 7 	 2	 1
13	 6 	 3	 1
16	 7 	 1	 2
17	 7 	 0	 1
18	 7 	 2	 3
21	 6 	 3	 1
24	 7 	 2	 1
===================================================
SERVER WRITERS LOGS
sSeq	 oVal	 wID
3	 5	 5
4	 6	 6
6	 4	 4
7	 7	 7
11	 5	 5
12	 6	 6
14	 4	 4
15	 7	 7
19	 5	 5
20	 6	 6
22	 4	 4
```
