(ns spelling-bee-cheater.core
  (:require [clojure.spec.alpha :as s]))


(def words-file "C:/Users/Victor/Desktop/spelling-bee-cheater/resources/engmix.txt")

(def bee-letters "ngruwpo")

(defn bee-word-valid?
  [word center-letter all-letters]
  (and
     (>= (.length word) 4)
     (.contains word center-letter)
     (clojure.set/subset? (set word) all-letters)))

(defn bee-cheat
  [letters]
  (let [center-letter (.substring letters 0 1)
        letters-set (set letters)]
    (with-open [rdr (clojure.java.io/reader words-file)]
      (doall (filter #(bee-word-valid? % center-letter letters-set) (line-seq rdr))))))
