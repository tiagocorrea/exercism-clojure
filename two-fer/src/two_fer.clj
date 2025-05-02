(ns two-fer)

(defn two-fer
  "Return what you will say as you give away the extra cookie"
  ([] "One for you, one for me.")
  ([name] (format "One for %s, one for me." name)))
