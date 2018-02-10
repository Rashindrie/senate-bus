# senate-bus

<h3>The Senate Bus problem </h3>

<b> Note: </b>This problem is taken from the book “Little book of Semaphores”, page 211.

This problem was originally based on the Senate bus at Wellesley College. Riders come to a bus stop and wait for a bus. When the bus arrives, all the waiting riders invoke boardBus, but anyone who arrives while the bus is boarding has to wait for the next bus. The capacity of the bus is 50 people; if there are more than 50 people waiting, some will have to wait for the next bus. When all the waiting riders have boarded, the bus can invoke depart. If the bus arrives when there are no riders, it should depart immediately. Note that busses and riders will continue to arrive throughout the day. Assume inter-arrival time of busses and riders are exponentially distributed with a mean of 20 min and 30 sec, respectively.

