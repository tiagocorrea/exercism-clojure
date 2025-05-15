(ns pig-latin
  (:require [clojure.string :as str]))

; Rule 1: If a word begins with a vowel sound,
; add an "ay" sound to the end of the word.
; Please note that "xr" and "yt" at the beginning of a word make vowel sounds
; (e.g. "xray" -> "xrayay", "yttria" -> "yttriaay").

; Rule 2: If a word begins with a consonant sound,
; move it to the end of the word and then add an "ay" sound to the end of the word.
; Consonant sounds can be made up of multiple consonants,
; a.k.a. a consonant cluster (e.g. "chair" -> "airchay").

; Rule 3: If a word starts with a consonant sound followed by "qu",
; move it to the end of the word, and then add an "ay" sound to the end of the word
; (e.g. "square" -> "aresquay").

; Rule 4: If a word contains a "y" after a consonant cluster or as the second letter
; in a two letter word it makes a vowel sound (e.g. "rhythm" -> "ythmrhay", "my" -> "ymay").

(defn rule-1 [word]
  (str word "ay"))

(defn rule-2-3 [word match]
  (str (subs word (count match)) match "ay"))

(defn rule-4 [word match]
  (if (= 2 (count word))
    (str (apply str (reverse word)) "ay")
    (str (subs word (dec (count match))) (apply str (drop-last match)) "ay")))

(defn translate-one [word]
  (let [match-1 (re-seq #"^[aeiou]|xr|yt" word)
        match-2 (re-seq #"^[^aeiou]+"     word)
        match-3 (re-seq #"^[^aeiou]?qu"   word)
        match-4 (re-seq #"^[^aeiou]+y{1}" word)]
    (cond
      (some? match-4) (rule-4   word (first match-4))
      (some? match-3) (rule-2-3 word (first match-3))
      (some? match-1) (rule-1   word)
      (some? match-2) (rule-2-3 word (first match-2)))))

(defn translate [s]
  (if (re-seq #"\s" s)
    (let [words (re-seq #"\w+" s)]
      (str/join " " (map translate-one words)))
    (translate-one s)))
