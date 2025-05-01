(ns nth-prime)

(defn prime?
  [n]
  (cond
    (< n 2) false
    (= n 2) true
    :else (->> (+ 1 (Math/sqrt n))
               (range 2)
               (filter #(= 0 (mod n %)))
               (empty?))))

(defn nth-prime
  "Returns the nth prime number."
  [n]
  ;; function body
  )
