function getTimeAgo(timestamp) {
    const currentTime = new Date();
    const targetTime = new Date(timestamp);

    const timeDifference = currentTime - targetTime;
    const seconds = Math.floor(timeDifference / 1000);

    if (seconds < 60) {
        return `${seconds} sec ago`;
    } else if (seconds < 3600) {
        const minutes = Math.floor(seconds / 60);
        return `${minutes} min ago`;
    } else if (seconds < 86400) {
        const hours = Math.floor(seconds / 3600);
        return `${hours} hr ago`;
    } else {
        // Calculate days using UTC dates to avoid timezone issues
        const days = Math.floor(timeDifference / (1000 * 60 * 60 * 24));
        return `${days} days ago`;
    }
}

export default getTimeAgo;
