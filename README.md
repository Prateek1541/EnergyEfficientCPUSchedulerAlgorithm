# EnergyEfficientCPUSchedulerAlgorithm
Develop a CPU scheduling algorithm that minimizes energy consumption without compromising performance. The algorithm should be suitable for mobile and embedded systems where power efficiency is critical.
Overview
This project focuses on developing an Energy Efficient CPU Scheduling Algorithm that minimizes energy consumption without compromising performance. It is specially designed for mobile and embedded systems where power efficiency is critical.

The main base of the algorithm is the Round Robin (RR) scheduling policy, which is modified and enhanced to achieve better energy performance. Comparative analysis with traditional CPU scheduling algorithms like FCFS, SJF, and Priority Scheduling is also included.

Features
🖥️ Energy-Aware Round Robin Scheduler

⚡ Minimized CPU energy consumption

📱 Suitable for mobile and embedded devices

🔄 Improved upon standard Round Robin technique

📊 Comparison with traditional scheduling algorithms
How It Works.....
Baseline: Standard Round Robin uses fixed time quantum and can cause frequent context switching, leading to unnecessary energy wastage.

Enhancement: The Energy Efficient Round Robin (EERR) dynamically adjusts the time quantum based on:

Process burst times

CPU load

Battery/energy status (simulated in code)

By reducing unnecessary context switches and intelligently managing CPU cycles, the algorithm conserves energy without affecting process completion time significantly.

