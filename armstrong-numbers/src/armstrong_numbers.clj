(ns armstrong-numbers)

(defn armstrong?
  "Returns true if the given number is an Armstrong number; otherwise, returns false"
  [num]
  (let [digits (map #(Integer/parseInt (str %)) (str num))
        len (count digits)]
    (= num
       (->> digits
            (map #(.pow (biginteger %) len))
            (apply +')))))
