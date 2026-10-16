# RaindanceMC/veintoggle

- **`veintoggle-module`** — Rain's Flexible Modules — Vein Toggle: Veinminer, switched on or off by each player for themselves. Veinminer itself is not touched and not depended on: the extra blocks of a vein are refused for whoever switched it off, and everything else Veinminer does stays as its owner configured it.
- **`veintoggle-standalone`** — Rain's Vein Toggle: Rain's Vein Toggle, as a plugin of its own: the veintoggle module and the standard wrapper, shaded together.

Part of [RaindanceMC](https://github.com/RaindanceMC); built on [RainsCore](https://github.com/RaindanceMC/RainsCore) and the shared parent in [framework](https://github.com/RaindanceMC/framework). Split out of `rains-flexible-modules` on 16.10.2026, history kept.

## Build

```
mvn install
```

Needs Java 25 and read access to `packages.tstieh.de` (server ids `packages-releases` / `packages-snapshots` in `~/.m2/settings.xml`).

## Plugins

- `veintoggle-standalone/target/RainsVeinToggle-0.5.0.jar` — also published as `de.raindancer.modules:veintoggle-standalone:0.5.0`

## Versions

A module's version is a property in this repository's root `pom.xml`, a plugin's is `plugin.version` in its own pom. CI publishes every version that is not in the registry yet on the next push to master.
