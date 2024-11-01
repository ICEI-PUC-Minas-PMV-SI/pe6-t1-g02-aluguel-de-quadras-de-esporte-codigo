"use client"

function pad(num: number) {
    let num2 = num.toString();
    while (num2.length < 2) num2 = "0" + num;
    return num2;
}

const isoDateFormatter = {
    formatDate: (isoDateStr: string) => {
    const date = new Date(isoDateStr)
    return `${pad(date.getDate())}/${pad(date.getMonth())}/${date.getFullYear()}`
    },
    formatTime: (isoDateStr: string) => {
        const date = new Date(isoDateStr)
        return `${pad(date.getHours())}:${pad(date.getMinutes())}`
    }
}

export default isoDateFormatter