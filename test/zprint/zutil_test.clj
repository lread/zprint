(ns zprint.zutil-test
  (:require [expectations :refer :all]
            [zprint.core :refer :all]
            [clojure.repl :refer :all]
            [clojure.string :as str]
            [rewrite-cljc.parser :as p]
            [rewrite-cljc.zip :as z]))

;; Keep some of the test from wrapping so they still work
;!zprint {:comment {:wrap? false}}

;
; Keep tests from configuring from any $HOME/.zprintrc or local .zprintrc
;

(set-options! {:configured? true})

;;
;; Test for ( def x :yabcdefghijklmnopqrstuvwxyz) which duplicates x because
;; znthnext didn't work correctly if there was whitespace at the start of a
;; seq.
;;

(def zz (z/edn* (p/parse-string "( 0 1 2 3 4 )")))

(expect "0" (z/string (zprint.zutil/znthnext zz 0)))
(expect "1" (z/string (zprint.zutil/znthnext zz 1)))
(expect "2" (z/string (zprint.zutil/znthnext zz 2)))
(expect "3" (z/string (zprint.zutil/znthnext zz 3)))
(expect "4" (z/string (zprint.zutil/znthnext zz 4)))
(expect nil (z/string (zprint.zutil/znthnext zz 5)))

(def za (z/edn* (p/parse-string "(0 1 2 3 4)")))

(expect "0" (z/string (zprint.zutil/znthnext za 0)))
(expect "1" (z/string (zprint.zutil/znthnext za 1)))
(expect "2" (z/string (zprint.zutil/znthnext za 2)))
(expect "3" (z/string (zprint.zutil/znthnext za 3)))
(expect "4" (z/string (zprint.zutil/znthnext za 4)))
(expect nil (z/string (zprint.zutil/znthnext za 5)))
