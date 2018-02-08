# Interview Question:

Design a ShippingFactory use case.

- Computer is the product, manufactured in Factory.  Each computer has unique serial number.

- 5 Assembly Machines produces Computers.  It can assemble one machine at a time, takes about 10sec to build it.
Once assembled, it loads to Shipping Belt.  Only one machine can be loaded on shipping belt at a given time.
If the Shipping Belt is full, then loading can't be done.  All 5 assembler machines run in parallel.

- 3 Trucks can be loaded with computers.  Loading step is done in batch of 5 computers at a time, takes about 50sec to load.
So there should be minimum of 5 computers on ShippingBelt when the load step can be initiated.
Only one Truck can access the ShippingBelt at a given time.

- Shipping Belt is one common component in factory.  You can either load computers on to it from Assemblers or unload computers on to Trucks.
It can hold max of 100 computers only.


# Solution Approach:

1. Design and Model key classes - Factory, ShippingBelt, Assembler, Truck & Computer.

2. The common ShippingBelt should be Singleton implementation with concurrency support.

3. The problem use case is the classic Multi threaded environment Producer & Consumer problem
- There are many ways to solve the Concurrency issues and the option I choose is to use BlockingQueue.
- BlockingQueue provides out-of-box support for blocking on put() and take()
- BlockingQueue solution is simpler and avoids writing confusing wait-notify code implementations.
