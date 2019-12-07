#!/bin/bash
pmdC="./pmd-bin-6.8.0/bin/run.sh pmd"

declare -a modules_list
modules_list=(
"app-management"
"balancer"
"config-server"
)

for module_position in "${!modules_list[@]}"
    do
        module_name="${modules_list[$module_position]}"
        echo "$module_position . $module_name"
        $pmdC -d "../$module_name/src/main" -R ./pmd-standart.xml -cache "./caches/$module_name.cache" -reportfile "./pmd.$module_name.xml" -f xml
        echo "$module_name finished"
    done