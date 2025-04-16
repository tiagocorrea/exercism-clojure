(ns collatz-conjecture)

(defn run-steps [n steps]
  (cond
    (<= n 1) steps
    (even? n) (recur (/ n 2) (inc steps))
    (odd? n) (recur (+ 1 (* n 3)) (inc steps))))

(defn collatz
  "Returns the number of steps it takes to reach 1 according
  to the rules of the Collatz Conjecture."
  [num]
  (run-steps num 0))
