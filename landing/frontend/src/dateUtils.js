export function addBusinessDays(startDate, numDays){
    const oneDay = 24 * 60 * 60 * 1000; // Milliseconds in a day
    let currentDate = new Date(startDate);

    // Loop through the specified number of business days
    for (let i = 0; i < numDays; i++) {
        currentDate = new Date(currentDate.getTime() + oneDay);
        // Check if the current day is a weekend (Saturday or Sunday)
        while (currentDate.getDay() === 0 || currentDate.getDay() === 6) {
            currentDate = new Date(currentDate.getTime() + oneDay);
        }
    }

    return currentDate;
}