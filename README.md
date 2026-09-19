# Java Multithreaded Chat Application

A console-based client-server chat application demonstrating Java socket programming, multithreading, and I/O streams.

## Features
- Multiple clients connected to one server
- Real-time message exchange
- Server-side broadcast to connected clients
- Graceful client connection/disconnection handling
- Console-based interface

## Tech Stack
- Java SE
- `java.net.Socket`
- `java.net.ServerSocket`
- Java Threads
- Java I/O Streams

## Architecture

```text
Client 1 ──┐
Client 2 ──┼──> Chat Server ──> Broadcast
Client 3 ──┘
```

Each client communicates with the server over a socket. The server uses separate execution threads to handle connected clients concurrently.

## Learning Outcomes
- TCP socket communication
- Concurrent client handling
- Thread lifecycle and synchronization concepts
- Stream-based network I/O

## Run

Start the server first, then launch one or more clients using the Java source files in the repository.