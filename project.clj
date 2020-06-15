(defproject zprint "1.0.0"
  :description "Pretty print zippers and s-expressions"
  :url "https://github.com/kkinnear/zprint"
  :license {:name "MIT License",
            :url "https://opensource.org/licenses/MIT",
            :key "mit",
            :year 2015}
  :plugins
  [[lein-expectations "0.0.8"] [lein-codox "0.10.3"] [lein-zprint "1.0.0"] [reifyhealth/lein-git-down "0.3.6"]]
  :middleware [lein-git-down.plugin/inject-properties]
  :profiles {:repl {:dependencies [#_[com.taoensso/tufte "1.1.1"]
                                   #_[org.clojure/clojurescript "1.9.946"]
                                   ;[rum "0.10.8"];
                                   [better-cond "1.0.1"]
				   #_[zpst "0.1.6"]
                                   [org.clojure/core.match "0.3.0-alpha5"]
                                   #_[clojure-future-spec "1.9.0-alpha17"]]},
	     :expectations {:dependencies [[expectations "2.1.10"]]}
             :uberjar {;:aot [zprint.core zprint.main],
                       ; For 1.9.0-alpha17, use this for the :aot value
                       :aot [zprint.core zprint.main clojure.core.specs.alpha],
                       :main zprint.main,
                       :dependencies [#_[clojure-future-spec "1.9.0-alpha17"]],
                       :omit-source true,
                       :uberjar-name "zprint-filter-%s"}}
  ; Clojure 1.8 you can exclude all sources in the uberjar
  ; :uberjar-exclusions [#"\.(clj|java|txt)"]
  ; Clojure 1.9 requires the .clj files in the uberjar
   :uberjar-exclusions [#"\.(clj\.|java|cljs|txt)"]
   :jar-exclusions [#"\.(clj$|clj\.|java|txt|cljs)"]
  :zprint {:old? false}
  :jvm-opts ^:replace
            ["-server" "-Xms2048m" "-Xmx2048m" "-Xss500m"
             "-XX:-OmitStackTraceInFastThrow"]
  :scm {:name "git", :url "https://github.com/kkinnear/zprint"}
  :codox {:namespaces [zprint.core],
          :doc-files
            ["README.md" "doc/bang.md" "doc/graalvm.md" "doc/filter.md"],
          :metadata {:doc/format :markdown}}
  :dependencies
    [#_[org.clojure/clojure "1.10.0"]
     #_[org.clojure/clojure "1.10.2-alpha1"]
     [org.clojure/clojure "1.9.0"]
     #_[org.clojure/clojure "1.8.0"]
     [borkdude/edamame "0.0.11-alpha.12"]
     [rewrite-cljc "0df7d6723062995956567b997c490bee8a6641e2"]]
  :repositories [["public-github" {:url "git://github.com"}]]
  :git-down {rewrite-cljc {:coordinates lread/rewrite-cljc-playground}})
