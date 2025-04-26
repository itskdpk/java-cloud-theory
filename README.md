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

Would you also like me to format it into a nice visual diagram to make it even more intuitive? 📊
