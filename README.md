# java-cloud-theory

### Concurrency vs Parallelism

**Concurrency** and **Parallelism** are two fundamental concepts in computing that are often confused but differ significantly in their execution approach.

#### Concurrency
In concurrency, multiple tasks are performed by *switching* between them. Although it may seem like the tasks are running simultaneously, in reality, they are not. Instead, a single processor rapidly alternates between tasks, giving the illusion of simultaneous execution.

**Example:**  
On a single-core computer, you might be playing a song while downloading a file. It appears that both activities are happening at the same time. However, the processor is actually switching between playing the song and downloading the file. At any given moment, it is only working on one task, but the switching happens so fast that it feels concurrent.

In a **single-threaded environment**, a single core can never perform two tasks truly simultaneously. It achieves multitasking through **context switching**, rapidly saving and restoring the state of tasks.



#### Parallelism
Parallelism, by contrast, involves executing multiple tasks *simultaneously* using multiple cores or processors. Each core can perform a separate task at the same time without the need for context switching.

**Example:**  
On a multi-core computer, one core can handle playing the song while another core simultaneously manages the file download. Since each task is assigned to a different core, they genuinely run at the same time.


### Key Differences
- **Concurrency** is about **dealing** with multiple tasks at once (through task switching).
- **Parallelism** is about **doing** multiple tasks at once (through true simultaneous execution).


### Application Scenarios
- An application can be **concurrent but not parallel** — it manages multiple tasks, but they do not run at the same time (e.g., single-core systems).
- An application can be **parallel but not concurrent** — it runs multiple tasks simultaneously, but each core is dedicated to a separate task without switching.
- An application can be **neither concurrent nor parallel** — it executes one task at a time sequentially.
- An application can be **both concurrent and parallel** — multiple tasks are performed simultaneously, and within each core, tasks can also be switched if there are more tasks than available cores.



**Time Slicing**  
The CPU allocates a fixed time slot (time slice) to each thread for execution. When a thread's time slice expires, the CPU performs a context switch and assigns the core to another thread.

**Context Switching**  
Context switching involves saving the current state of a running task and loading the saved state of another task. This allows the CPU to switch between different tasks, enabling concurrency on single-core systems.

---

Thread States
-> New
-> Runnable
-> Running
-> Blocked
-> Waiting
-> Timed_Waiting
-> Dead 

LifeCycle of Thread
-> NEW → RUNNABLE → start()
-> RUNNABLE → BLOCKED → Waiting for a lock
-> RUNNABLE → WAITING → wait(), join()
-> RUNNABLE → TIMED_WAITING → sleep(), wait(timeout)
-> TERMINATED → Thread has finished execution


public static void main(String[] args) {
  TicketBookingSystem bookingSystem = new TicketBookingSystem(1000);

  Thread physicalThread = new Thread(new PhysicalBooking(bookingSystem));
  Thread digitalThread = new Thread(new DigitalBooking(bookingSystem));

  System.out.println("Starting ticket booking system with 1000 tickets...");

  physicalThread.start();
  digitalThread.start();

  try {
      physicalThread.join();
      digitalThread.join();
  } catch (InterruptedException e) {
      e.printStackTrace();
  }

  System.out.println("All tickets booked. Program ending.");
}

Thread methods

join() 
-> Join method makes the current thread to wait for completion of the thread on which join method is called.
When Main Thread call newThread().join(), it makes Main thread enter waiting state this execution completion of newThread. When newThread terminates it calls notifyAll to wake up main Thread for it's execution.
In Above code, the Main Thread will wait for completion of physicalThread, once the physical thread terminates main thread continues its executions, then again call digitalThread.join(), where main thread checks 
If digitalThread is still executing or completed, if digitalThread still executing then the main thread agin goes into waiting state or Timed_Waiting state.
If digitalThread was completed by the time digitalThread.join() called by main, the main doesn't goes into waiting state and continues it's execution.

sleep()
-> Thread.sleep(1000) makes the current thread moved into Waiting state
-> If Thread holds locks before going to sleep, it'll continues holding the lock even after going to sleep

wait()
-> wait method() called to make current thread move to waiting State indefinetlly until notifies and release any lock acquired
-> Anytime wait() is executed by a Thread, it'll went into the waitSet of the Object where this wait() is called. Each Object have WaitSet(It's a set collection to store thread in waiting state).

notify() 
-> Notify notifies any one of the Thread waiting in waitset

notifyAll()
-> NotifyAll notifies all the Thread waiting in waitset

yield()
-> Thread.yield(), suggest CPUs to take the CPU if any high priority Thread is in waiting state.
