class BankAccount (){
     var balance: Long = 0
         private set
         get() { check(open); return field }
    private var open: Boolean = true

    fun adjustBalance(amount: Long){
        synchronized(this) {
            check(balance + amount >= 0)
            balance += amount
        }
    }
    fun close() {
        check(open)
        open = false
    }
}
