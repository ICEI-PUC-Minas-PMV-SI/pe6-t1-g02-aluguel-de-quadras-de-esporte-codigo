"use client"

import { useState } from 'react'
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogFooter } from "@/components/ui/dialog"
import { Button } from "@/components/ui/button"
import { Calendar } from "@/components/ui/calendar"
import { Label } from "@/components/ui/label"
import { Input } from "@/components/ui/input"

interface RescheduleModalProps {
  isOpen: boolean
  onClose: () => void
  reservation: {
    id: string
    date: string
    startTime: string
    endTime: string
  } | null
  onReschedule: () => void
}

export function RescheduleModal({ isOpen, onClose, reservation, onReschedule }: RescheduleModalProps) {
  const [date, setDate] = useState<Date | undefined>(reservation ? new Date(reservation.date) : undefined)
  const [startTime, setStartTime] = useState(reservation?.startTime || '')
  const [endTime, setEndTime] = useState(reservation?.endTime || '')

//   const handleReschedule = async () => {
//     if (!reservation || !date || !startTime || !endTime) return

//     try {
//       await apiService.updateReservation(reservation.id, {
//         date: format(date, 'yyyy-MM-dd'),
//         startTime,
//         endTime
//       })
//       onReschedule()
//       onClose()
//     } catch (error) {
//       console.error('Failed to reschedule reservation', error)
//       // Here you might want to show an error message to the user
//     }
//   }

  return (
    <Dialog open={isOpen} onOpenChange={onClose}>
      <DialogContent className="sm:max-w-[425px]">
        <DialogHeader>
          <DialogTitle>Reschedule Reservation</DialogTitle>
        </DialogHeader>
        <div className="grid gap-4 py-4">
          <div className="grid gap-2">
            <Label htmlFor="date">Date</Label>
            <Calendar
              mode="single"
              selected={date}
              onSelect={setDate}
              className="rounded-md border"
            />
          </div>
          <div className="grid gap-2">
            <Label htmlFor="start-time">Start Time</Label>
            <Input
              id="start-time"
              type="time"
              value={startTime}
              onChange={(e) => setStartTime(e.target.value)}
            />
          </div>
          <div className="grid gap-2">
            <Label htmlFor="end-time">End Time</Label>
            <Input
              id="end-time"
              type="time"
              value={endTime}
              onChange={(e) => setEndTime(e.target.value)}
            />
          </div>
        </div>
        <DialogFooter>
          <Button variant="outline" onClick={onClose}>Cancel</Button>
          {/* <Button onClick={handleReschedule}>Reschedule</Button> */}
        </DialogFooter>
      </DialogContent>
    </Dialog>
  )
}