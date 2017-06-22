(defproject cards "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.229"]
                 [reagent "0.6.0"]
                 [reagent-forms "0.5.29"]
                 [re-frame "0.9.4"]]

  :plugins [[lein-cljsbuild "1.1.4"]
            [lein-sassy "1.0.8"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj"]

  :clean-targets ^{:protect false} [:target-path
                                    [:cljsbuild :builds :dev :compiler :output-dir]
                                    [:cljsbuild :builds :prod :compiler :output-to]
                                    [:sass :dst]]

  :figwheel {:css-dirs ["resources/public/css"]}

  :cljsbuild {:builds {:dev {:source-paths ["src/cljs"]
                             :figwheel     {:on-jsload "cards.core/mount-root"}
                             :compiler     {:main                 cards.core
                                            :output-to            "resources/public/js/compiled/app.js"
                                            :output-dir           "resources/public/js/compiled/out"
                                            :asset-path           "js/compiled/out"
                                            :source-map-timestamp true
                                            :preloads             [devtools.preload]
                                            :external-config      {:devtools/config {:features-to-install :all}}}}

                       :prod {:source-paths ["src/cljs"]
                              :compiler     {:main            cards.core
                                             :output-to       "resources/public/js/compiled/app.js"
                                             :optimizations   :advanced
                                             :output-wrapper  true
                                             :closure-defines {goog.DEBUG false}
                                             :pretty-print    false}}}}

  :sass {:src "resources/sass"
         :dst "resources/public/css/"
         :style :expanded}

  :profiles {:dev {:dependencies [[binaryage/devtools "0.8.2"]
                                  [figwheel-sidecar "0.5.10"]]
                   :plugins      [[lein-figwheel "0.5.10"]]}
             :prod {:sass {:style :compressed}}}

  :aliases {"prod" ["do"
                    "clean"
                    ["with-profile" "prod" "sass" "once"]
                    ["cljsbuild" "once" "prod"]]})
