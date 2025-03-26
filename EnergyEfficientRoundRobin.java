import java.util.*;

public class EnergyEfficientRoundRobin {
    // Function to determine dynamic time slice based on energy usage
    public static int getDynamicTimeSlice(int energyUsage) {
        if (energyUsage <= 2) {
            return 6; // Low energy usage → Longer time slice (6 units)
        } else if (energyUsage <= 5) {
            return 4; // Medium energy usage → Normal time slice (4 units)
        } else {
            return 2; // High energy usage → Shorter time slice (2 units)
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: User input for number of processes and time quantum
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        // Step 2: Arrays for process details
        int[] Process_id = new int[n];
        int[] Arrival_time = new int[n];
        int[] Burst_time = new int[n];
        int[] Remaining_burst_time = new int[n];
        int[] Completion_time = new int[n];
        int[] Turnaround_time = new int[n];
        int[] Waiting_time = new int[n];
        int[] Response_time = new int[n];
        int[] Energy_usage = new int[n];  // Energy usage of each process
        int[] Time_quantum = new int[n];  // Time quantum for each process
        boolean[] isFirstResponse = new boolean[n];

        // Step 3: Input details for each process (Arrival time, Burst time, and Energy usage)
        for (int i = 0; i < n; i++) {
            Process_id[i] = i + 1;
            System.out.print("Enter arrival time of P" + Process_id[i] + ": ");
            Arrival_time[i] = sc.nextInt();
            System.out.print("Enter burst time of P" + Process_id[i] + ": ");
            Burst_time[i] = sc.nextInt();
            System.out.print("Enter energy usage (1-10) for P" + Process_id[i] + ": ");
            Energy_usage[i] = sc.nextInt();
            Remaining_burst_time[i] = Burst_time[i];
            isFirstResponse[i] = true;

            // Calculate the time quantum for each process based on energy usage
            Time_quantum[i] = getDynamicTimeSlice(Energy_usage[i]);
        }

        // Step 4: Process scheduling logic using Round Robin
        Queue<Integer> queue = new LinkedList<>();
        int currentTime = 0, completed = 0;
        boolean[] isInQueue = new boolean[n];

        while (completed < n) {
            // Add processes to the queue based on arrival time and remaining burst time
            for (int i = 0; i < n; i++) {
                if (Arrival_time[i] <= currentTime && !isInQueue[i] && Remaining_burst_time[i] > 0) {
                    queue.add(i);
                    isInQueue[i] = true;
                }
            }

            // If the queue is empty, just increment time
            if (queue.isEmpty()) {
                currentTime++;
                continue;
            }

            // Process execution
            int idx = queue.poll();

            if (isFirstResponse[idx]) {
                Response_time[idx] = currentTime - Arrival_time[idx];
                isFirstResponse[idx] = false;
            }

            // Calculate the execution time for the current process
            int execTime = Math.min(Time_quantum[idx], Remaining_burst_time[idx]);
            currentTime += execTime;
            Remaining_burst_time[idx] -= execTime;

            // Requeue the process if it is not finished
            if (Remaining_burst_time[idx] > 0) {
                queue.add(idx);
            } else {
                // Process has completed
                Completion_time[idx] = currentTime;
                Turnaround_time[idx] = Completion_time[idx] - Arrival_time[idx];
                Waiting_time[idx] = Turnaround_time[idx] - Burst_time[idx];
                completed++;
            }
        }

        // Step 5: Display the results in a table format
        System.out.println("\nProcess\tAT\tBT\tEnergy Usage\tTime Quantum\tCT\tTAT\tWT\tRT");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + Process_id[i] + "\t" + Arrival_time[i] + "\t" + Burst_time[i] + "\t" + Energy_usage[i] + "\t\t" + Time_quantum[i] + "\t\t" + Completion_time[i] + "\t" + Turnaround_time[i] + "\t" + Waiting_time[i] + "\t" + Response_time[i]);
        }

        sc.close();
    }
}
